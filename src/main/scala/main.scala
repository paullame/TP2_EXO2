import org.apache.spark.graphx.{Edge, Graph, VertexRDD}
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}


object main {

  def main(args: Array[String]) {

    val conf = new SparkConf().setAppName("TP2_EXO2").setMaster("local")

    val sc= new SparkContext(conf)
    sc.setLogLevel("ERROR")

    val solar = new Creature("Solar", 363, 18, 44, 4, 35, 6, 3, 50)
    val worgRider1 = new Creature("Worg Rider", 13, 6, 18, 1, 6, 8, 1, 20)
    val worgRider2 = new Creature("Worg Rider", 13, 6, 18, 1, 6, 8, 1, 20)
    val worgRider3 = new Creature("Worg Rider", 13, 6, 18, 1, 6, 8, 1, 20)
    val worgRider4 = new Creature("Worg Rider", 13, 6, 18, 1, 6, 8, 1, 20)
    val worgRider5 = new Creature("Worg Rider", 13, 6, 18, 1, 6, 8, 1, 20)
    val worgRider6 = new Creature("Worg Rider", 13, 6, 18, 1, 6, 8, 1, 20)
    val worgRider7 = new Creature("Worg Rider", 13, 6, 18, 1, 6, 8, 1, 20)
    val worgRider8 = new Creature("Worg Rider", 13, 6, 18, 1, 6, 8, 1, 20)
    val worgRider9 = new Creature("Worg Rider", 13, 6, 18, 1, 6, 8, 1, 20)
    val barbare1 = new Creature("Barbare", 142, 10, 17, 3, 19, 8, 1, 40)
    val barbare2 = new Creature("Barbare", 142, 10, 17, 3, 19, 8, 1, 40)
    val barbare3 = new Creature("Barbare", 142, 10, 17, 3, 19, 8, 1, 40)
    val barbare4 = new Creature("Barbare", 142, 10, 17, 3, 19, 8, 1, 40)
    val warlord = new Creature("Warlord", 141, 10, 27, 3, 20, 8, 1, 30)

    //creation de l'array de Creatures
    val creatureArray = Array(
      solar,
      worgRider1,
      worgRider2,
      worgRider3,
      worgRider4,
      worgRider5,
      worgRider6,
      worgRider7,
      worgRider8,
      worgRider9,
      barbare1,
      barbare2,
      barbare3,
      barbare4,
      warlord
    )
    val indexedCreatureArray= creatureArray.zipWithIndex.map{case (creature, index) => (index.toLong, creature)}

    //creation de l'array des arretes (créatures placées entre 50 et 500 ft
    var indexedEdgeArray = for (j <- 1 until creatureArray.length) yield Edge(0, j.toLong, scala.util.Random.nextInt(450)+50)

    //creation des RDDs
    var vertexRDD: RDD[(Long, Creature)] = sc.parallelize(indexedCreatureArray)
    var edgeRDD: RDD[Edge[Int]] = sc.parallelize(indexedEdgeArray)


    //creation du graphe
    val defaultCreature = new Creature("creature inconnue", 1, 0, 0, 0, 0, 0, 0, 0)
    val graph: Graph[ Creature,Int] = Graph(vertexRDD, edgeRDD, defaultCreature)


    println("on imprime les vertices")
    graph.vertices.collect.foreach{
      case (id, creature) => println(s"name is ${creature.nom}")
    }

    println("on imprime les Edges")
    graph.edges.map(e => e.attr).count

    println("opération sur triplets")
    for (triplet <- graph.triplets.collect) {
      println(s"${triplet.srcAttr.nom} is ${triplet.attr} ft away from ${triplet.dstAttr.nom} ")
    }




    //graph.aggregateMessages[Int](ctx => ctx.sendToDst(1), _ + _)


    println("DEBUT FONCTION AGGREGATE")

    val olderFollowers: VertexRDD[(Message)] = graph.aggregateMessages[(Message)](
      triplet => { // Map Function

        def message = Message(20,100)
        triplet.sendToSrc(message)


      },
      // Add counter and age
      (a, b) => Message(a.damage + b.damage, a.deplacement + b.deplacement) // Reduce Function
    )

    val result: Graph[Creature, Int] =graph.joinVertices(olderFollowers)((id, crea, message ) => crea.update(message))
    val result2: Graph[Creature, Int] = graph.mapEdges(e => {
      def message =Message(20,100)
      e.attr - message.deplacement
    })

    println("on imprime les vertices")
    result.vertices.collect.foreach{
      case (id, creature) => println(s"la creature est ${creature.nom} et ses hp sont ${creature.vie} ")
    }

    println("opération sur triplets")
    for (triplet <- result2.triplets.collect) {
      println(s"${triplet.srcAttr.nom} is ${triplet.attr} ft away from ${triplet.dstAttr.nom} ")
    }





  }

}

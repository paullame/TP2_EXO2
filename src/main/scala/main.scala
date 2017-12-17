import org.apache.spark.graphx.{Edge, Graph, VertexRDD}
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}


object main {

  def main(args: Array[String]) {

    val conf = new SparkConf().setAppName("TP2_EXO2").setMaster("local")

    val sc= new SparkContext(conf)
    sc.setLogLevel("ERROR")

    val indexedCreatureArray = initialisationCreatures


    //creation de l'array des arretes créatures placées entre 50 et 500 ft)
    val indexedEdgeArray = for (j <- 1 until indexedCreatureArray.length-1) yield Edge(1L, j.toLong, scala.util.Random.nextInt(450) + 50)

    //creation des RDDs
    val vertexRDD: RDD[(Long, Creature)] = sc.parallelize(indexedCreatureArray)
    val edgeRDD: RDD[Edge[Int]] = sc.parallelize(indexedEdgeArray)


    //creation du graphe
    val defaultCreature = new Creature("creature inconnue",0L, 1, 0, 0, 0, 0, 0, 0, 0, true)
    val graph: Graph[ Creature,Int] = Graph(vertexRDD, edgeRDD, defaultCreature)


    //on affiche les sommets
    println("on affiche les vertices")
    graph.vertices.collect.foreach{
      case (id, creature) => println(s"la creature est ${creature.nom}, son vertexId est ${id} et ses hp sont ${creature.vie} ")
    }

    //on affiche les arretes
    // Ca marche pas, a investiguer
    println("on affiche les Edges")
    graph.edges.map(e => e.attr).count


    //on affiche les triplets
    println("on affiche sur triplets")
    for (triplet <- graph.triplets.collect) {
      println(s"${triplet.srcAttr.nom} is ${triplet.attr} ft away from ${triplet.dstAttr.nom} ")
    }



    // 2 fonction sndMessages et mergeMessages
    //logique de fusion des messages
    // @TODO passer les fonctions anynomes dans de vraies fonctions
    println("DEBUT FONCTION AGGREGATE")
    val olderFollowers: VertexRDD[(Message)] = graph.aggregateMessages[(Message)](
      triplet => { // Map Function


        if(triplet.dstId==2L)        {
          if(triplet.attr>)
          def gentils = Message(triplet.srcAttr.attaqueMelee(triplet.dstAttr))
          triplet.sendToDst(gentils)

        }

        def mechants = Message(triplet.dstAttr.attaqueMelee(triplet.srcAttr)) // message est une case class donc pas besoin d'utiliser le mot clé "new"
        triplet.sendToSrc(mechants)
        



      },
      // Add damage and deplacement
      // @TODO retirer déplacement car c'est géré par les arretes et pas par les messages
      (a, b) => Message(a.damage + b.damage) // Reduce Function
    )

    // update des distances dans les arretes
    val result: Graph[Creature, Int] =graph.joinVertices(olderFollowers)((id, crea, message ) => crea.update(message))

/*    val result2: Graph[Creature, Int] = graph.mapEdges(e => {
      def message =Message(20,100)
      e.attr - message.deplacement
    })*/


    println("on imprime les vertices")
    result.vertices.collect.foreach{
      case (id, creature) => println(s"la creature est ${creature.nom} et ses hp sont ${creature.vie} ")
    }

/*    println("opération sur triplets")
    for (triplet <- result2.triplets.collect) {
      println(s"${triplet.srcAttr.nom} is ${triplet.attr} ft away from ${triplet.dstAttr.nom} ")
    }*/





  }

  def initialisationCreatures(): Array[(Long, Creature)] = {

    def solar = new Creature("Solar",1L, 363, 18, 44, 4, 35, 6, 3, 50,true)
    def worgRider1 = new Creature("Worg Rider",2L, 13, 6, 18, 1, 6, 8, 1, 20, false)
    def worgRider2 = new Creature("Worg Rider",3L, 13, 6, 18, 1, 6, 8, 1, 20, false)
    def worgRider3 = new Creature("Worg Rider",4L, 13, 6, 18, 1, 6, 8, 1, 20, false)
    def worgRider4 = new Creature("Worg Rider",5L, 13, 6, 18, 1, 6, 8, 1, 20, false)
    def worgRider6 = new Creature("Worg Rider",6L, 13, 6, 18, 1, 6, 8, 1, 20, false)
    def worgRider7 = new Creature("Worg Rider",7L, 13, 6, 18, 1, 6, 8, 1, 20, false)
    def worgRider5 = new Creature("Worg Rider",8L, 13, 6, 18, 1, 6, 8, 1, 20, false)
    def worgRider8 = new Creature("Worg Rider",9L, 13, 6, 18, 1, 6, 8, 1, 20, false)
    def worgRider9 = new Creature("Worg Rider",10L, 13, 6, 18, 1, 6, 8, 1, 20, false)
    def barbare1 = new Creature("Barbare",11L ,142, 10, 17, 3, 19, 8, 1, 40, false)
    def barbare2 = new Creature("Barbare",12L ,142, 10, 17, 3, 19, 8, 1, 40, false)
    def barbare3 = new Creature("Barbare",13L ,142, 10, 17, 3, 19, 8, 1, 40, false)
    def barbare4 = new Creature("Barbare",14L ,142, 10, 17, 3, 19, 8, 1, 40, false)
    def warlord = new Creature("Warlord",15L , 141, 10, 27, 3, 20, 8, 1, 30, false)

    //creation de l'array de Creatures
    def creatureArray = Array(
      (solar.vertexId, solar),
      (worgRider1.vertexId, worgRider1),
      (worgRider2.vertexId, worgRider2),
      (worgRider3.vertexId, worgRider3),
      (worgRider4.vertexId, worgRider4),
      (worgRider5.vertexId, worgRider5),
      (worgRider6.vertexId, worgRider6),
      (worgRider7.vertexId, worgRider7),
      (worgRider8.vertexId, worgRider8),
      (worgRider9.vertexId, worgRider9),
      (barbare1.vertexId, barbare1),
      (barbare2.vertexId, barbare2),
      (barbare3.vertexId, barbare3),
      (barbare4.vertexId, barbare4),
      (warlord.vertexId, warlord)
    )

    creatureArray

  }


}

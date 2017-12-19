import org.apache.spark.graphx.{Edge, EdgeTriplet, Graph, VertexRDD}
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}


object main {

  def main(args: Array[String]) {

    val conf = new SparkConf().setAppName("TP2_EXO2").setMaster("local")

    val sc = new SparkContext(conf)
    sc.setLogLevel("ERROR")

    val indexedCreatureArray = initCombat1()


    // creation of the graph
    val graph = GraphMaker(indexedCreatureArray, sc)


    afficherVertices(graph)
    afficherTriplets(graph)


    val resultDeplacement: Graph[Creature, Int] = graph.mapTriplets(e => {
      e.dstAttr.checkCanAttack(e.attr)
      if (!e.dstAttr.canAttack) {
        e.dstAttr.deplacer(e.attr)
      }
      else {
        e.attr
      }
    })



    // 2 fonction sndMessages et mergeMessages
    //logique de fusion des messages
    // @TODO passer les fonctions anynomes dans de vraies fonctions
    println("DEBUT FONCTION AGGREGATE")
    val olderFollowers: VertexRDD[(Message)] = resultDeplacement.aggregateMessages[(Message)](
      triplet => { // Map Function

        //TODO logique d'attaque = le solar attaque un monstre aleatoirement, ou le plus près.
        if (triplet.dstId == 2L) {

          def gentils = Message(triplet.srcAttr.attaqueMelee(triplet.dstAttr))

          triplet.sendToDst(gentils)

        }

        if (triplet.dstAttr.canAttack) {
          def mechants = Message(triplet.dstAttr.attaqueMelee(triplet.srcAttr)) // message est une case class donc pas besoin d'utiliser le mot clé "new"
          triplet.sendToSrc(mechants)
        }


      },
      // Add damage
      (a, b) => Message(a.damage + b.damage) // Reduce Function
    )


    // update des distances dans les arretes
    val resultCombat: Graph[Creature, Int] = resultDeplacement.joinVertices(olderFollowers)((id, crea, message) => crea.update(message))


    afficherVertices(resultCombat)
    afficherTriplets(resultCombat)


  }


  def initCombat1(): Array[(Long, Creature)] = {

    val solar: Creature = new Solar(1L)
    val worgRider1 = new WorgRider(2L)
    val worgRider2 = new WorgRider(3L)
    val worgRider3 = new WorgRider(4L)
    val worgRider4 = new WorgRider(5L)
    val worgRider6 = new WorgRider(6L)
    val worgRider7 = new WorgRider(7L)
    val worgRider5 = new WorgRider(8L)
    val worgRider8 = new WorgRider(9L)
    val worgRider9 = new WorgRider(10L)
    val barbare1 = new Barbare(11L)
    val barbare2 = new Barbare(12L)
    val barbare3 = new Barbare(13L)
    val barbare4 = new Barbare(14L)
    val warlord = new Warlord(15L)

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

  def initCombat2(): Array[(Long, Creature)] = {

    val solar: Creature = new Solar(1L)
    //TODO
    //1 Solar 2 Planetar, 2 Monavic Dena, 5 Astral Deva
    //1 dragon, 200 OrcBarbare, 10 AngelSlayer
    //utiliser un map pour initialiser les 200 orcs

    //creation de l'array de Creatures
    def creatureArray = Array(
      (solar.vertexId, solar)
    )

    creatureArray

  }


  /*
   * this method produce the graph of the creature
   */
  def GraphMaker(indexedCreatureArray : Array[(Long, Creature)], sc : SparkContext) : Graph[Creature, Int] = {

    //creation de l'array des arretes créatures placées entre 50 et 500 ft)
    val indexedEdgeArray = for (j <- 2 until indexedCreatureArray.length - 1) yield Edge(1L, j.toLong, scala.util.Random.nextInt(50) + 110)

    //creation des RDDs
    val vertexRDD: RDD[(Long, Creature)] = sc.parallelize(indexedCreatureArray)
    val edgeRDD: RDD[Edge[Int]] = sc.parallelize(indexedEdgeArray)


    //creation du graphe
    val defaultCreature = new WorgRider(0L)

    //val graph: Graph[Creature, Int] = Graph(vertexRDD, edgeRDD, defaultCreature)
    val graph = Graph(vertexRDD, edgeRDD, defaultCreature)
    return graph
  }

  def afficherVertices(graph: Graph[Creature, Int]): Unit = {
    println("on imprime les vertices du graphe" + graph.toString)
    graph.vertices.collect.foreach {
      case (id, creature) => println(s"la creature est ${creature.nom}, son vertexId est ${id} et et ses hp sont ${creature.vie} ")
    }
    println("\n")
    println("\n")

  }

  def afficherTriplets(graph: Graph[Creature, Int]): Unit = {
    println("on imprime sur triplets du graphe" + graph.toString)
    for (triplet <- graph.triplets.collect) {
      println(s"${triplet.srcAttr.nom} is ${triplet.attr} ft away from ${triplet.dstAttr.nom} ")
    }
    println("\n")
    println("\n")

  }


}

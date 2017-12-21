import org.apache.spark.graphx.{Edge, EdgeTriplet, Graph, VertexRDD}
import org.apache.spark.rdd.RDD
import org.apache.spark.util.LongAccumulator
import org.apache.spark.{SparkConf, SparkContext}


object main {

  def main(args: Array[String]) {

    val conf = new SparkConf().setAppName("TP2_EXO2").setMaster("local")

    val sc = new SparkContext(conf)
    sc.setLogLevel("ERROR")

    //...............EXERCICE 1 :
    val indexedCreatureArray = initCombat1()
    // creation of the graph
    var graph = GraphMakerEx1(indexedCreatureArray, sc)

    afficherVertices(graph)
    afficherTriplets(graph)


    val NB_GENTILS1: Long= 1
    val NB_MECHANTS1: Long = 14

    var mortsGentilsEx1: LongAccumulator = sc.longAccumulator("Nombre de gentils morts Exercice 1")
    var mortsMechantsEx1: LongAccumulator = sc.longAccumulator("Nombre de méchants morts Exercuce 1")






    //attaque ex 1
    while (mortsGentilsEx1.value < NB_GENTILS1 || mortsMechantsEx1.value < NB_MECHANTS1) {

      //deplacement des creatures
      graph = graph.mapTriplets(e => {
        e.dstAttr.checkCanAttack(e.attr)
        if (!e.dstAttr.canAttack) { //when the dstAttr creature are too far
          e.dstAttr.deplacerEx1(e.attr)
        }
        else {
          e.attr
        }
      })

      // 2 fonctions sndMessages et mergeMessages
      //logique de fusion des messages

      val vertex = attack()

      //this method launch the attaqueMelee send a message with the dammages value
      def attack(): VertexRDD[(Message)] ={

        println("DEBUT FONCTION AGGREGATE")
        val vertex: VertexRDD[(Message)] = graph.aggregateMessages[(Message)](
          triplet => { // Map Function

            //TODO logique d'attaque = le solar attaque un monstre aleatoirement, ou le plus près.
            if (triplet.dstId == 2L) {

              triplet.srcAttr.regeneration()

              //attaqueMelee on triplet.dstAttr, return the damages
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

        vertex

      }


      //envoie des dégats
      graph = graph.joinVertices(vertex)((_, crea, message) => crea.update(message))

      graph.vertices.collect()foreach {
        case (_, creature) =>
          if(creature.isDead && creature.equipe) {
            mortsGentilsEx1.add(1)
          }
          else if(creature.isDead && !creature.equipe) {
            mortsMechantsEx1.add(1)
          }

      }

      //suppression des morts
      graph = graph.subgraph(e => e.srcAttr.isDead || e.dstAttr.isDead, (_, creature) => creature.isDead)

      afficherVertices(graph)
      afficherTriplets(graph)

    }




    //...............EXERCICE 2 :
    val indexedCreatureArrayGentils2 = initCombat2Gentils()
    val indexedCreatureArrayMechants2 = initCombat2Mechants()

    // creation of the graph:
    var graph2 = GraphMakerEx2(sc)

    val NB_GENTILS2: Long = 10
    val NB_MECHANTS2: Long = 211

    var mortsGentilsEx2: LongAccumulator = sc.longAccumulator("Nombre de gentils morts Exercice 2")
    var mortsMechantsEx2: LongAccumulator = sc.longAccumulator("Nombre de méchants morts Exercice 2")


    //attaque exercice 2
    var continue = true
    while (continue) {










      if(mortsGentilsEx2.value >= NB_GENTILS2 || mortsMechantsEx2.value >= NB_MECHANTS2)
        continue = false


    }


  }








  def initCombat1(): Array[(Long, Creature)] = {

    val solar: Creature = new Solar(1L)
    val worgRider1: Creature = new WorgRider(2L)
    val worgRider2: Creature = new WorgRider(3L)
    val worgRider3: Creature = new WorgRider(4L)
    val worgRider4: Creature = new WorgRider(5L)
    val worgRider6: Creature = new WorgRider(6L)
    val worgRider7: Creature = new WorgRider(7L)
    val worgRider5: Creature = new WorgRider(8L)
    val worgRider8: Creature = new WorgRider(9L)
    val worgRider9: Creature = new WorgRider(10L)
    val barbare1: Creature = new Barbare(11L)
    val barbare2: Creature = new Barbare(12L)
    val barbare3: Creature = new Barbare(13L)
    val barbare4: Creature = new Barbare(14L)
    val warlord: Creature = new Warlord(15L)

    //creation de l'array de Creatures
    val creatureArray = Array(
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

  def initCombat2Gentils(): Array[(Long, Creature)] = {

    //GENTILS
    val solar: Creature = new Solar(1L)
    val platenar1: Creature = new Planetar(2L)
    val platenar2: Creature  = new Planetar(3L)
    val monavicDena1: Creature = new MonavicDena(4L)
    val monavicDena2: Creature = new MonavicDena(5L)
    val astralDena1: Creature = new AstralDena(6L)
    val astralDena2: Creature = new AstralDena(7L)
    val astralDena3: Creature = new AstralDena(8L)
    val astralDena4: Creature = new AstralDena(9L)
    val astralDena5: Creature = new AstralDena(10L)



    val firstArray: Array[(Long, Creature)] = Array(
      (solar.vertexId,solar),
      (platenar1.vertexId,platenar1),
      (platenar2.vertexId,platenar2),
      (monavicDena1.vertexId,monavicDena1),
      (monavicDena2.vertexId,monavicDena2),
      (astralDena1.vertexId,astralDena1),
      (astralDena2.vertexId,astralDena2),
      (astralDena3.vertexId,astralDena3),
      (astralDena4.vertexId,astralDena4),
      (astralDena5.vertexId,astralDena5)

    )

    val creatureArray = firstArray
    creatureArray.foreach(c => println(c))
    creatureArray

  }


  def initCombat2Mechants(): Array[(Long, Creature)] = {
    //MECHANTS
    val greatWyrm: Creature = new GreatWyrm(11L)

    val greatArray: Array[(Long, Creature)] = Array(
      (greatWyrm.vertexId,greatWyrm)
    )

    val slayerArray: Array[(Long,Creature)] = Array.tabulate(10) { i =>
      ((i+12).toLong,new AngelSlayer(i+12))
    }


    val orcbArray: Array[(Long,Creature)] = Array.tabulate(100) { i =>
      ((i+22).toLong,new OrcBarbare(i+22))
    }


    val creatureArray = greatArray++slayerArray++orcbArray
    creatureArray.foreach(c => println(c))
    creatureArray

  }



    /*
   * this method produce the graph of the creature for the EXERCICE 1
   */
  def GraphMakerEx1(indexedCreatureArray : Array[(Long, Creature)], sc : SparkContext) : Graph[Creature, Int] = {

    //creation de l'array des arretes créatures placées entre 110  et 160 ft)
    val indexedEdgeArray = for (j <- 2 until indexedCreatureArray.length - 1) yield Edge(1L, j.toLong, scala.util.Random.nextInt(50+1) + 110)

    //creation des RDDs
    val vertexRDD: RDD[(Long, Creature)] = sc.parallelize(indexedCreatureArray)
    val edgeRDD: RDD[Edge[Int]] = sc.parallelize(indexedEdgeArray)


    //creation du graphe
    val defaultCreature = new WorgRider(0L)

    //val graph: Graph[Creature, Int] = Graph(vertexRDD, edgeRDD, defaultCreature)
    val graph = Graph(vertexRDD, edgeRDD, defaultCreature)
    graph
  }






  /*
 * this method produce the graph of the creature for the EXERCICE 2
 */
  def GraphMakerEx2( sc : SparkContext) : Graph[Creature, Int] = {

    val indexedCreatureArrayGentils2 = initCombat2Gentils()
    val indexedCreatureArrayMechants2 = initCombat2Mechants()


    //creation of the array between each "gentils" and all the "mechants" with a random distance between 50 and 500 ft
    val edgesArray =
      for (compt1 <- 0 until indexedCreatureArrayGentils2.length; compt2 <- 0 until indexedCreatureArrayMechants2.length)
        yield Edge(compt1.toLong, (indexedCreatureArrayMechants2.length + compt2).toLong, scala.util.Random.nextInt(450+1) + 50)

    //creation des RDDs
    val vertexRDD: RDD[(Long, Creature)] = sc.parallelize(indexedCreatureArrayGentils2 ++ indexedCreatureArrayMechants2 )
    val edgeRDD: RDD[Edge[Int]] = sc.parallelize(edgesArray)


    //creation du graphe
    val defaultCreature = new WorgRider(0L)

    //val graph: Graph[Creature, Int] = Graph(vertexRDD, edgeRDD, defaultCreature)
    val graph = Graph(vertexRDD, edgeRDD, defaultCreature)
    graph
  }









  def afficherVertices(graph: Graph[Creature, Int]): Unit = {
    println("on imprime les vertices du graphe" + graph.toString)
    graph.vertices.collect.foreach {
      case (id, creature) => println(s"la creature est ${creature.nom}, son vertexId est $id et et ses hp sont ${creature.vie} ")
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

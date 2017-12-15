import org.apache.spark.graphx.{Edge, Graph}
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}


object main {

  def main(args: Array[String]) {

    val conf = new SparkConf().setAppName("TP2_EXO2").setMaster("local")

    val sc= new SparkContext(conf)
    sc.setLogLevel("ERROR")

    val solar = new Creature("Solar")
    val orc = new Creature("orc")
    val dragon = new Creature("dragon")

    //creation de l'array de Creatures
    val creatureArray = Array(solar, orc, dragon)
    val indexedCreatureArray= creatureArray.zipWithIndex.map{case (creature, index) => (index.toLong, creature)}

    //creation de l'array des arretes
    val indexedEdgeArray = for (i <- 0 ; j <- creatureArray.indices) yield Edge(, j.toLong, 10)

    //creation des RDDs
    val vertexRDD: RDD[(Long, Creature)] = sc.parallelize(indexedCreatureArray)
    val edgeRDD: RDD[Edge[Int]] = sc.parallelize(indexedEdgeArray)


    //creation du graphe
    val defaultCreature = new Creature("creature inconnue")
    val graph: Graph[ Creature,Int] = Graph(vertexRDD, edgeRDD, defaultCreature)


    println("on imprime les vertices")
    graph.vertices.collect.foreach{
      case (id, creature) => println(s"name is ${creature.nom}")
    }

    println("on imprime les Edges")
    graph.edges.map(e => e.attr).count

    println("opération sur triplets")
    for (triplet <- graph.triplets.collect) {
      println(s"${triplet.srcAttr.nom} is ${triplet.attr} meters away from ${triplet.dstAttr.nom} ")
    }

  }

}

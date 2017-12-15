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


    val creatureArray = Array(solar, orc, dragon)
    val indexedCreatureArray= creatureArray.zipWithIndex.map{case (creature, index) => (index.toLong, creature)}


    val edgeArray = Array(
      Edge(1L, 2L, 10 ),
      Edge(1L, 3L, 20 )
    )
    val result = for (i <- 0 until creatureArray.length; j <- (i + 1) until creatureArray.length) yield Edge(i.toLong, j.toLong, 10)


    val vertexRDD: RDD[(Long, Creature)] = sc.parallelize(indexedCreatureArray)
    val edgeRDD: RDD[Edge[Int]] = sc.parallelize(result)

    val graph: Graph[ Creature,Int] = Graph(vertexRDD, edgeRDD)


    println("on imprime les vertices")
    graph.vertices.collect.foreach{
      case (id, creature) => println(s"name is $creature")
    }

/*    println("on imprime les Edges")
    graph.edges.collect.foreach{
      case (id, distance) => println(s"name is $distance")
    }*/

    println("opération sur triplets")
    for (triplet <- graph.triplets.collect) {
      println(s"${triplet.srcAttr} is ${triplet.attr} meters away from ${triplet.dstAttr} ")
    }

  }

}

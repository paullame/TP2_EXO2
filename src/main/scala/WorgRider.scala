import org.apache.spark.graphx.VertexId

/*
 * This class extends creature and implement
 * specific attrributes and fight method
 */


class WorgRider(monVertexId: Long) extends Creature(monVertexId) {

  val nom = "Worg Rider"
  var vie = 13
  var vieMax: Int = 13
  val atk = 6
  val arm = 18
  val nbAtk = 1
  val prec = 6
  val atkDice = 8
  val nbDice = 1
  val vit = 20
  var canAttack = false
  val regen: Int = 0
  val equipe = false //gentils = true et méchants = false


  override def attaquer(creature: Creature, vertexId: VertexId): Int = {

    attaqueMelee(creature)

  }



}

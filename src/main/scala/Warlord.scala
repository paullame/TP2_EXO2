import org.apache.spark.graphx.VertexId

/*
 * This class extends creature and implement
 * specific attrributes and fight method
 */

class Warlord(monVertexId: Long) extends Creature(monVertexId) {

  val nom = "Warlord"
  var vie = 141
  var vieMax: Int = 141
  val atk = 10
  val arm = 27
  val nbAtk = 3
  val prec = 20
  val atkDice = 8
  val nbDice = 1
  val vit = 30
  var canAttack = false
  val regen: Int = 0
  val equipe = false // gentils = true et méchants =false

   def attaquer(creature: Creature, vertexId: VertexId): Int = {
    attaqueMelee(creature)
  }







}

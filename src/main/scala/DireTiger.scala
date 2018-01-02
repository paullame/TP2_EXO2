import org.apache.spark.graphx.VertexId

class DireTiger(monVertexId: VertexId, monEquipe: Boolean) extends Creature(monVertexId){
  val nom: String = "Dire Tiger"
  var vie: Int = 105
  val vieMax: Int = 105
  val arm: Int = 17

  val meleeAtk: Int = 8
  val meleenbAtk: Int = 1
  val meleePrec: Int = 18
  val meleeAtkDice: Int = 4
  val meleenbDice: Int = 2

  val distAtk: Int = 0
  val distnbAtk: Int = 0
  val distPrec: Int = 0
  val distAtkDice: Int = 0
  val distnbDice: Int = 0

  val vit: Int = 40
  var canAttack: Boolean = false
  val equipe: Boolean = monEquipe
  val regen: Int = 0


  def attaquer(creature: Creature, distance: Int): Int = {


0
  }


}

import org.apache.spark.graphx.VertexId

/*
 * This class extends creature and implement
 * specific attrributes and fight method
 */

class Solar(monVertexId: Long) extends Creature(monVertexId) {

  val nom = "Solar"
  var vie = 363
  val vieMax: Int = 363
  val arm = 44
  val reducDmg = 15

  val meleeAtk = 18
  val meleenbAtk = 4
  val meleePrec = 35
  val meleeAtkDice = 6
  val meleenbDice = 3

  val distAtk: Int = 14
  val distnbAtk: Int = 4
  val distPrec: Int = 31
  val distAtkDice: Int = 2
  val distnbDice: Int = 6


  val vit = 50
  var canAttack = true
  val regen = 15
  val equipe = true //gentils = true et méchants = false



  def attaquer(creature: Creature, distance: Int): Int = {
    if (distance > 5) {
      attaqueDistance(creature)
    }
    else {
      attaqueMelee(creature)

    }
  }


  override def update(message: Message): Creature = {
    println(nom+" perd "+message.damage+" points de vie")
    vie -= Math.max(0,message.damage - reducDmg)
    this
  }



}

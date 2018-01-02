import org.apache.spark.graphx.VertexId

class OrcBarbare(monVertexId: VertexId) extends Creature(monVertexId){
  val nom: String = "Greataxe Orc"
  var vie: Int = 42
  val vieMax: Int = 42
  val arm: Int = 15


  val meleeAtk: Int = 10
  val meleenbAtk: Int = 1
  val meleePrec: Int = 11
  val meleeAtkDice: Int = 12
  val meleenbDice: Int = 1

  val distAtk: Int = 0
  val distnbAtk: Int = 0
  val distPrec: Int = 0
  val distAtkDice: Int = 0
  val distnbDice: Int = 0

  val vit: Int = 30
  var canAttack: Boolean = false
  val regen: Int = 0
  val equipe: Boolean = false //true = gentils false = méchants

  def attaquer(creature: Creature, distance: Int): Int = {
    attaqueMelee(creature)
  }






/*  def attaqueRanged(creature : Creature): Int ={
     var damages = 0
     var attackDispo: Int = nbAtk
     val strengh = Array(16, 11, 6) // composite longbow
     var i = 0

    while (attackDispo > 0 && vie > 0){

      if(i<2)
        i += 1


      var RangedValue = 0
      if (creature.vie > 0) {
        var a = 0
        for(a <- 1 to 3){ //longbow repetition
          RangedValue += strengh(i)
          RangedValue += Dice.launch(1, 8)
          RangedValue += 6
        }


      }
      if (RangedValue >= creature.arm) {

        creature.takeDamage(RangedValue)
        damages += RangedValue
        if (creature.vie <= 0)
          creature.die()

      }
      attackDispo -= 1
    }

    damages
  }*/

}

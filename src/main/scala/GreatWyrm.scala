import org.apache.spark.graphx.VertexId

/**
  * Created by Etudes on 19/12/2017.
  */
class GreatWyrm (monVertexId: VertexId) extends Creature(monVertexId) {

  val nom: String = "Green Dragon, Great Wyrm"
  var vie: Int = 391
  val vieMax: Int = 391
  val arm: Int = 37


  val meleeAtk: Int = 21
  val meleenbAtk: Int = 1
  val meleePrec: Int = 33
  val meleeAtkDice: Int = 8
  val meleenbDice: Int = 4

  val distAtk: Int = 0
  val distnbAtk: Int = 0
  val distPrec: Int = 0
  val distAtkDice: Int = 0
  val distnbDice: Int = 0

  val vit: Int = 250
  var canAttack: Boolean = true
  val regen: Int = 0
  val equipe: Boolean = false

  def attaquer(creature: Creature, distance: Int): Int = {
0
  }







/*  //this method implement the special attack of the greatWyrm
  def specialAttack(creature: Creature): Int = {

    var damages = 0
    var attackDispo: Int = nbAtk


    //small creature
    if(creature.vieMax<=100){

      while (attackDispo > 0 && vie > 0){
        var SpecialValue = 0
        if (creature.vie > 0) {
          SpecialValue += Dice.launch(24, 6) //acid
          SpecialValue += 31
        }
        if (SpecialValue >= creature.arm) {
          creature.takeDamage(SpecialValue)
          damages += SpecialValue
          if (creature.vie <= 0)
            creature.die()
        }
      }

    }

    //medium creature
    if(creature.vieMax>100 && creature.vieMax <= 200 ){

      while (attackDispo > 0 && vie > 0){
        var specialValue = 0
        if (creature.vie > 0) {
          specialValue += Dice.launch(2, 8)
          specialValue += 21

        }
        if (specialValue >= creature.arm) {
          creature.takeDamage(specialValue)
          damages += specialValue
          if (creature.vie <= 0)
            creature.die()
        }
      }

    }

    //large creature
    if(creature.vieMax > 300){

      while (attackDispo > 0 && vie > 0){
        var specialValue = 0
        if (creature.vie > 0) {
          specialValue += Dice.launch(4, 8)
          specialValue += 21
        }
        if (specialValue >= creature.arm) {
          creature.takeDamage(specialValue)
          damages += specialValue
          if (creature.vie <= 0)
            creature.die()
        }
      }

      attackDispo -= 1

    }


    damages

  }*/

}

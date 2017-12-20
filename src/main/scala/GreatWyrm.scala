import org.apache.spark.graphx.VertexId

/**
  * Created by Etudes on 19/12/2017.
  */
class GreatWyrm (monVertexId: VertexId) extends Creature(monVertexId) {
  val nom: String = "Green Dragon, Great Wyrm"
  var vie: Int = 391
  val vieMax: Int = 391
  val atk: Int = 27
  val arm: Int = _
  val nbAtk: Int = _
  val prec: Int = 37
  val atkDice: Int = 8
  val nbDice: Int = 4
  val vit: Int = _
  var canAttack: Boolean = _
  val regen: Int = 10
  val equipe: Boolean = _

  def attaquer(creature: Creature, vertexId: VertexId): Int = {
    val damages = specialAttack(creature)
    return damages
  }

  def attaqueMelee(creature: Creature): Int = {

    var attackDispo: Int = nbAtk
    var precision: Int = prec
    var totalDamage: Int = 0
    val strengh = Array(33, 33, 31, 31)
    var i = 0

    while (attackDispo > 0 && vie > 0)
    {

      //strenth counter
      if(i<3)
        i += 1


      if (creature.vie > 0) {
        var calc: Int = (Dice.launch(4, 8) + precision)
        calc += strengh(i)
        val touch = 2 * calc
        if (touch >= creature.arm) {
          val degat = Dice.launch(nbDice, atkDice) + atk
          creature.takeDamage(degat)
          totalDamage += degat
          if (creature.vie <= 0)
            creature.die()
        }
      }
      precision -= 5
      attackDispo -= 1
    }
    totalDamage
  }




  //this method implement the special attack of the greatWyrm
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


    return damages

  }


}

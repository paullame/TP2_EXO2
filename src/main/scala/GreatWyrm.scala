import org.apache.spark.graphx.VertexId

/**
  * Created by Etudes on 19/12/2017.
  */
class GreatWyrm (monVertexId: VertexId) extends Creature(monVertexId) {
  val nom: String = "Green Dragon, Great Wyrm"
  var vie: Int = 391
  val vieMax: Int = 391
  val atk: Int = 21
  val arm: Int = 37
  val nbAtk: Int = 1 //TODO Attaque jet d'acide, sort de zone.
  val prec: Int = 33
  val atkDice: Int = 8
  val nbDice: Int = 4
  val vit: Int = 250
  var canAttack: Boolean = true
  val regen: Int = 0
  val equipe: Boolean = false

  def attaquer(creature: Creature, vertexId: VertexId): Int = {
    val damages = specialAttack(creature)
    damages
  }

  //this methode chech the edge attribute (distance) and if
  // the creature are less than an distance we can attack
  override def checkCanAttack(edgeAttr: Int): Unit = {
    if (edgeAttr > 20) { //reach
      canAttack = false
    }
    else {
      canAttack = true
    }

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


    damages

  }


}

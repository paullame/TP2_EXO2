import org.apache.spark.graphx.VertexId

class OrcBarbare(monVertexId: VertexId) extends Creature(monVertexId){
  val nom: String = "Greataxe Orc (Orc Barbarian 4)"
  var vie: Int = 42
  val vieMax: Int = 42
  val atk: Int = 4
  val arm: Int = _
  val nbAtk: Int = _
  val prec: Int = 15
  val atkDice: Int = 12
  val nbDice: Int = 1
  val vit: Int = 40
  var canAttack: Boolean = _
  val regen: Int = 0
  val equipe: Boolean = _

  def attaquer(creature: Creature, vertexId: VertexId): Int = {
    val damages = attaqueMelee(creature)
    return damages
  }


  //this methode chech the edge attribute (distance) and if
  // the creature are less than an distance we can attack
  override def checkCanAttack(edgeAttr: Int): Unit = {
    if (edgeAttr > 2) { //default
      canAttack = false
    }
    else {
      canAttack = true
    }

  }


  def attaqueMelee(creature: Creature): Int = {

    var attackDispo: Int = nbAtk
    var precision: Int = prec
    var totalDamage: Int = 0

    while (attackDispo > 0 && vie > 0)
    {
      if (creature.vie > 0) {
        val calc: Int = (Dice.launch(1, 8) + precision)
        val touch = 10 + calc
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



  def attaqueRanged(creature : Creature): Int ={
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

    return damages
  }


}

import org.apache.spark.graphx.VertexId

class AngelSlayer(monVertexId: VertexId) extends Creature(monVertexId){
  val nom: String = "Angel Slayer (Half-Orc Ranger 15)"
  var vie: Int = 112
  val vieMax: Int = 112
  val atk: Int = 15
  val arm: Int = 23+6
  val nbAtk: Int = 14
  val prec: Int = 24
  val atkDice: Int = 8
  val nbDice: Int = 1
  val vit: Int = 40
  var canAttack: Boolean = _
  val equipe: Boolean = _
  val regen: Int = _


  def attaquer(creature: Creature, vertexId: VertexId): Int = {
    val damages = attaqueRanged(creature)
    return damages
  }

  //this methode chech the edge attribute (distance) and if
  // the creature are less than an distance we can attack
  override def checkCanAttack(edgeAttr: Int): Unit = {
    if (edgeAttr > 2) { //default 2
      canAttack = false
    }
    else {
      canAttack = true
    }

  }


  def attaqueRanged(creature : Creature): Int ={
    var damages = 0
    var attackDispo: Int = nbAtk
    val strengh = Array(19, 14, 9) // composite longbow
    var i = 0

    while (attackDispo > 0 && vie > 0){

      if(i<2){ //counter for the stregh
        i += 1
      }

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







}

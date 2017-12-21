import org.apache.spark.graphx.VertexId

class AngelSlayer(monVertexId: VertexId) extends Creature(monVertexId){
  val nom: String = "Angel Slayer (Half-Orc Ranger 15)"
  var vie: Int = 112
  val vieMax: Int = 112
  val atk: Int = 21
  val arm: Int = 26
  val nbAtk: Int = 3
  val prec: Int = 21
  val atkDice: Int = 8
  val nbDice: Int = 1
  val vit: Int = 40
  var canAttack: Boolean = false
  val equipe: Boolean = false //true = gentils false = méchants
  val regen: Int = 0


  def attaquer(creature: Creature, vertexId: VertexId): Int = {
    val damages = attaqueRanged(creature)
    damages
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








}

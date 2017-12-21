import org.apache.spark.graphx.VertexId

class OrcBarbare(monVertexId: VertexId) extends Creature(monVertexId){
  val nom: String = "Greataxe Orc (Orc Barbarian 4)"
  var vie: Int = 42
  val vieMax: Int = 42
  val atk: Int = 10
  val arm: Int = 15
  val nbAtk: Int = 1
  val prec: Int = 11
  val atkDice: Int = 12
  val nbDice: Int = 1
  val vit: Int = 30
  var canAttack: Boolean = false
  val regen: Int = 0
  val equipe: Boolean = false //true = gentils false = méchants

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

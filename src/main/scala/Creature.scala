import org.apache.spark.graphx.VertexId

import scala.util.Random



abstract class Creature(monVertexId: Long) extends Serializable {

  //attributes
  val nom: String
  val vertexId: Long = monVertexId
  var vie: Int
  val vieMax: Int
  val arm: Int

  //MELEE
  val meleeAtk: Int
  val meleenbAtk: Int
  val meleePrec: Int
  val meleeAtkDice: Int
  val meleenbDice: Int

  //DISTANCE
  val distAtk: Int
  val distnbAtk: Int
  val distPrec: Int
  val distAtkDice: Int
  val distnbDice: Int

  val vit: Int
  var canAttack: Boolean
  val regen: Int
  val equipe: Boolean //gentils = true et méchants = false




  // Attack creature based on atk and nbAtk
  def attaquer(creature: Creature, distance: Int): Int



  def attaqueMelee(creature: Creature): Int = {
    var attackLeft: Int = meleenbAtk
    var precisionLeft: Int = meleePrec
    var totalDamage: Int = 0
    var pvEnemie: Int = creature.vie

    while (attackLeft > 0 && vie > 0) {
      if (pvEnemie > 0) {
        val touch: Int = Dice.launch(1, 20) + precisionLeft
        if (touch >= creature.arm) {
          val degat = Dice.launch(meleenbDice, meleeAtkDice) + meleeAtk
          pvEnemie -= degat
          totalDamage += degat

        }
      }
      precisionLeft -= 5
      attackLeft -= 1
    }
    totalDamage

  }

  def attaqueDistance(creature: Creature): Int = {
    var attackLeft: Int = distnbAtk
    var precisionLeft: Int = distPrec
    var totalDamage: Int = 0
    var pvEnemie: Int = creature.vie

    while (attackLeft > 0 && vie > 0) {
      if (pvEnemie > 0) {
        val touch: Int = Dice.launch(1, 20) + precisionLeft
        if (touch >= creature.arm) {
          val degat = Dice.launch(distnbDice, distAtkDice) + distAtk
          pvEnemie -= degat
          totalDamage += degat

        }

      }
      precisionLeft -= 5
      attackLeft -= 1
    }
    totalDamage
  }



  //this methode chech the edge attribute (distance) and if
  // the creature are less than an distance we can attack
  def checkCanAttack(edgeAttr: Int): Unit = {
    if (edgeAttr > 2) {
      canAttack = false
    }
    else {
      canAttack = true
    }

  }

  //deplacement vers le solar
  def deplacerEx1(edgeAttr: Int): Int = {
    //println("le "+nom+" se déplace de "+vit+" pieds")
    val zero = 0
    var distance = edgeAttr - vit

    //reduce the distance between the "mechant" and the solar check if value is > 0
    if (distance > zero)
      distance
    else
      zero

  }

  /*  def choseTarget(): VertexId = {

    }*/





  //TODO completer la fonction
  //TODO ajouter la logique de qui attaque qui
  def update(message: Message): Creature = {
    println(nom+" perd "+message.damage+" points de vie")
    this.vie -= message.damage
    this
  }


  def regeneration(): Unit = {
    println(nom+" regenere "+regen+" points de vie ")
    //limit the level of live during the regeneration
    // at the top for the live value (vieMax)
    vie += math.min(regen, vieMax-vie)

  }


  def isDead: Boolean = {
    if (vie<=0) {
      true
    }
    else {
      false
    }
  }

}



abstract class Creature(monNom: String, monVertexId: Long, maVie: Int, baseAttack: Int, armure: Int, nbAttaquesParTour: Int,precisionPremAttaque: Int, desAttaque: Int , nbDes: Int, vitesse: Int, monEquipe: Boolean) extends Serializable {

  val nom: String = monNom
  val vertexId: Long = monVertexId
  var vie:    Int = maVie
  val atk:    Int = baseAttack
  val arm:    Int = armure
  val nbAtk:  Int = nbAttaquesParTour
  val prec:   Int = precisionPremAttaque
  val atkDice:Int = desAttaque
  val nbDice :Int = nbDes
  val vit:    Int = vitesse
  val equipe: Boolean = monEquipe //gentils = true et méchants = false

  // Generate nbDice random number(s) between 1 and max
  def dice (nbDice: Int, max: Int): Int =
  {
    val r = scala.util.Random
    var tot: Int = 0
    var diceLeft = nbDice
    while (diceLeft > 0)
      {
        tot = tot + r.nextInt( max ) + 1
        diceLeft -= 1
      }
    tot
  }

  def takeDamage (degat: Int): Unit =
  {
    vie -= degat
  }

  // Attack creature based on atk and nbAtk
  abstract def attaqueMelee (creature: Creature /* , distance: Int*/): Int =
  {
    var attackLeft: Int = nbAtk
    var precisionLeft: Int = prec
    var totalDamage:Int = 0

    while (attackLeft > 0 && vie>0) // && distance <= 5)
      {
        if (creature.vie > 0)
          {
            val touch: Int = dice(1,20)+precisionLeft
            if (touch >= creature.arm)
              {
                val degat = dice(nbDice,atkDice) + atk
                creature.takeDamage(degat)
                totalDamage += degat
                if (creature.vie <= 0)
                  creature.die()
              }
          }
        precisionLeft -= 5
        attackLeft -= 1
      }
    totalDamage
  }

/*  def choseTarget(): VertexId = {

  }*/


  def die() : Unit=
  {
    this.vie = 0
  }

  //TODO completer la fonction
  //TODO ajouter la logique de qui attaque qui
  def update(message: Message): Creature = {
    this.vie-=message.damage
    this
  }

}

/**
  * Object Dice simulate a roll
  */
object Dice {

  /*This method generate a random value of the diceNum roll of facesNum dice
  * parameter :  diceNum the number of launch ; facesNum the number of face (issues) of the dice (ex 6)
  * output : the number generated randomly
  */
  def launch(diceNum : Int, facesNum : Int) : Int={

    val r = scala.util.Random
    var sum = 0
    var compt = 0

    for(compt <- 1 to diceNum){
      sum += r.nextInt(facesNum)
      sum += 1
    }
    sum
  }

}

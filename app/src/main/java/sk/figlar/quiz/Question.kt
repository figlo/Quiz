package sk.figlar.quiz

data class Question(
    val id: Int,
    val text: String,
    val image: Int,
    val options: List<String>,
    val correctAnswer: Int,
)
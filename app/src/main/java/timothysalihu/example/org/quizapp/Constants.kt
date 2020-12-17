package timothysalihu.example.org.quizapp

object Constants{

    const val USER_NAME: String = "user_name"
    const val TOTAL_QUESTIONS: String = "total_questions"
    const val CORRECT_ANSWERS: String = "correct_answers"

    fun getQuestions(): ArrayList<Question>{
        val questionsList = ArrayList<Question>()

        val que1 = Question(
            1,
            "What is the name of the structure labelled 1?",
            R.drawable.ic_anatomy_heart,
            "Pulmonary Vein",
            "Right Atrium",
            "Superior Vena Cava",
            "Inferior Vena Cava",
            3
        )

        questionsList.add(que1)

        val que2 = Question(
            2,
            "What is the name of the structure labelled 2?",
            R.drawable.ic_anatomy_heart,
            "Right Atrium",
            "Superior Vena Cava",
            "Right Ventricle",
            "Tricuspid Valve",
            1
        )

        questionsList.add(que2)

        val que3 = Question(
            3,
            "What is the name of the structure labelled 3?",
            R.drawable.ic_anatomy_heart,
            "Tendinous Cords",
            "Tricuspid Valve",
            "Right Ventricle",
            "Right Atrium",
            2
        )

        questionsList.add(que3)

        val que4 = Question(
            4,
            "What is the name of the structure labelled 4?",
            R.drawable.ic_anatomy_heart,
            "Intervent. Septum",
            "Tendinous Cords",
            "Papillary Muscle",
            "Right Ventricle",
            4
        )

        questionsList.add(que4)

        val que5 = Question(
            5,
            "What is the name of the structure labelled 5?",
            R.drawable.ic_anatomy_heart,
            "Papillary Muscle",
            "Tendinous Cords",
            "Tricuspid Valve",
            "Right Ventricle",
            1
        )

        questionsList.add(que5)

        val que6 = Question(
            6,
            "What is the name of the structure labelled 6?",
            R.drawable.ic_anatomy_heart,
            "Pulmonary Vein",
            "Superior Vena Cava",
            "Pulmonary Artery",
            "Aorta",
            4
        )

        questionsList.add(que6)

        val que7 = Question(
            7,
            "What is the name of the structure labelled 7?",
            R.drawable.ic_anatomy_heart,
            "Pulmonary Artery",
            "Superior Vena Cava",
            "Pulmonary Vein",
            "Aorta",
            1
        )

        questionsList.add(que7)

        val que8 = Question(
            8,
            "What is the name of the structure labelled 8?",
            R.drawable.ic_anatomy_heart,
            "Bicuspid Valve",
            "Left Atrium",
            "Pulmonary Vein",
            "Papillary Muscle",
            2
        )

        questionsList.add(que8)

        val que9 = Question(
            9,
            "What is the name of the structure labelled 9?",
            R.drawable.ic_anatomy_heart,
            "Tricuspid Valve",
            "Tendinous Cords",
            "Papillary Muscle",
            "Bicuspid Valve",
            4
        )

        questionsList.add(que9)

        val que10 = Question(
            10,
            "What is the name of the structure labelled 10?",
            R.drawable.ic_anatomy_heart,
            "Papillary Muscle",
            "Left Ventricle",
            "Intervent. Septum",
            "Tendinous Cords",
            3
        )

        questionsList.add(que10)

        return questionsList
    }
}
package seg3x02.booksapigraphql.resolvers.types

class CreateEmployeeInput (
    val name: String = "",
    val dateOfBirth: String  = "",
    val city: String = "",
    val salary: Float = 50000.0f,
    val gender: String? = "",
    val email: String?  = ""
)

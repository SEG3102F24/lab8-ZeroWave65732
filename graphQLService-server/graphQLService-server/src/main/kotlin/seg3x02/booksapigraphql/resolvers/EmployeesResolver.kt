package seg3x02.booksapigraphql.resolvers

import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Controller
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.MutationMapping
import seg3x02.booksapigraphql.entity.Employee
import seg3x02.booksapigraphql.repository.EmployeesRepository
import seg3x02.booksapigraphql.resolvers.types.CreateEmployeeInput

@Controller
class EmployeesResolver(private val mongoOperations: MongoOperations,
                        private val employeesRepository: EmployeesRepository
) {
    @QueryMapping
    fun Employees(@Argument employeeNumber: Int): List<Employee> {
        val query = Query()
        query.addCriteria(Criteria.where("employeeNumber").`is`(employeeNumber))
        return mongoOperations.find(query, Employee::class.java)
    }

    @MutationMapping
    fun newEmployee(@Argument("createEmployeeInput") input: CreateEmployeeInput) : Employee {
        val employee = Employee(
            name = input.name,
            dateOfBirth = input.dateOfBirth,
            city = input.city,
            salary = input.salary,
            gender = input.gender,
            email = input.email
        )
        employeesRepository.save(employee)
        return employee

    }
}
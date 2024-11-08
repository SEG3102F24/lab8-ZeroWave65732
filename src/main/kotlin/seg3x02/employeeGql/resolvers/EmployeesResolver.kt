package seg3x02.employeeGql.resolvers

import org.springframework.stereotype.Controller

@Controller
class EmployeesResolver(val mongoOperations: MongoOperations,
                        private val EmployeesRepository) {
    @QueryMapping
    fun employees(@Argument employeeNumber: Int) List<Employee> {
        val query = Query()
        query.addCriteria(Criteria.where("employeeNumber").`is`(employeeNumber))
        return monoOperations.find(query, Employee::class.java)
    }

    @MutationMapping
    fun newEmployee(@Argument("createEmployeeInput") input: CreateEmployeeInput) : Employee {
        val employee = Employee(input.name,
                                input.dateOfBirth,
                                input.city,
                                input.salary,
                                input.gender,
                                input.email)
        employeesRepository.save(employee)
        return employee
    }

}
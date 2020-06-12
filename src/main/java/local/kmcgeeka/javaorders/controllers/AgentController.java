package local.kmcgeeka.javaorders.controllers;
import local.kmcgeeka.javaorders.models.Agents;
import local.kmcgeeka.javaorders.services.AgentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/agents")
public class AgentController
{
    @Autowired
    private AgentsService agentsService;
//
//    http://localhost:2019/agents/agent/9
//    GET /agents/agent/{id} - Returns the agent and their customers with the given agent id
//
@GetMapping(value = "/agent/{id}", produces = {"application/json"})
public ResponseEntity<?> findAgentById(@PathVariable long id){
    Agents a = agentsService.findAgentByCode(id);
    return new ResponseEntity<>(a, HttpStatus.OK);
}

}

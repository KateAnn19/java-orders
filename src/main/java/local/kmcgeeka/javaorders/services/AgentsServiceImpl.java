package local.kmcgeeka.javaorders.services;

import local.kmcgeeka.javaorders.models.Agents;

import local.kmcgeeka.javaorders.repositories.AgentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service(value = "agentsService")

public class AgentsServiceImpl implements AgentsService
{
    @Autowired
    private AgentsRepository agentrepos;
    //this creates the object I want to use inside this class
    //Spring sets it up automatically (by using @Autowired)

    @Override
    public Agents findAgentByCode(long id){
        return agentrepos.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Agent " + id + "Not Found"));
        //returns object instead of id
    }

}

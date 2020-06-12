package local.kmcgeeka.javaorders.services;

import local.kmcgeeka.javaorders.models.Agents;


public interface AgentsService
{
    Agents findAgentByCode(long id);

}

package com.example.projectboard;

import com.example.projectboard.domain.ProjectTask;
import com.example.projectboard.service.ProjectTaskService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MockMvcExampleTests {
    @Autowired
    private ProjectTaskService p;

    @Test
    public void exampleTest() throws Exception {
        ProjectTask projectTask = new ProjectTask();
        projectTask.setStatus("TO_DO");
        projectTask.setAcceptanceCritteria("Some");
        projectTask.setSummary("summary");
        p.saveOrUpdateProjectTask(projectTask);

        Iterable<ProjectTask> tasks = p.findAll();
        int counter = 0;
        for( ProjectTask p : tasks){
            counter++;
        }
        assertEquals(counter,1);
    }
}

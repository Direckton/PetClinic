import org.example.Model.Medicine;
import org.example.Model.Visit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class VisitTest {

    Visit visit;
    @BeforeEach
    void init()
    {
         visit = new Visit(1, LocalDateTime.of(2024,2,1,13,0),0.0f,new ArrayList<Medicine>());
    }
    @Test
    void visitWasHeld() {
        visit.visitWasHeld();
        assertTrue(visit.getHeld());
    }

    @Test
    void addMedicine() {
        visit.addMedicine(new Medicine("Ibuprofen",2.0f,1));
        assertSame("Ibuprofen",visit.getMedicines().get(0).getName());
        assertEquals(2.0f,visit.getMedicines().get(0).getQuantity());
        assertEquals(1,visit.getMedicines().get(0).getFrequency());
    }
}
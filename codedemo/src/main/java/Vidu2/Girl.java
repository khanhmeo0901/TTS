package Vidu2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Girl {
    @Autowired
    Outfit outfit;

    public Girl(Outfit outfit)
    {
        this.outfit = outfit;
    }
    //GET
    //SET
}

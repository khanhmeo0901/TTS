package Vidu3;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("bikini")
public class Bikini implements Outfit {
    @Override
    public void wear()
    {
        System.out.println("Mặc bikini");
    }
}

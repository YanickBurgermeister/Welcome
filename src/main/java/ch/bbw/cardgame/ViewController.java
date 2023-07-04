package ch.bbw.cardgame;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * ViewController
 *    Kontrolliert Zusammenspiel mit der View
 * @author Peter Rutschmann
 * @date 26.08.2021
 */
@Controller
public class ViewController {
    List<Car> leftCarList = new ArrayList<>();
    List<Car> rightCarList = new ArrayList<>();
    Car leftCar = null;
    Car rightCar = null;

    public ViewController() {
        setup();
    }

    private void setup(){
        leftCarList.clear();
        rightCarList.clear();

        //Aufwendiger Style
        Car car1 = new Car("images/Auto.png", "Audi", "Flaschback 300", 50000.00 );
        leftCarList.add(car1);
        Car car2 = new Car("images/Auto.png", "Opel", "Manta SE", 20000.00 );
        leftCarList.add(car2);
        Car car3 = new Car("images/Auto.png", "VW", "Golf GL", 12000.00 );
        leftCarList.add(car3);

        //Objekt direkt erstellen
        rightCarList.add(new Car("images/Auto.png", "Fiat", "500", 15000.00 ));
        rightCarList.add(new Car("images/Auto.png", "Alfa Romeo", "Guilietta", 30000.00 ));
        rightCarList.add(new Car("images/Auto.png", "Lancia", "Delta", 45000.00 ));
    }

    @GetMapping("/")
    public String redirect() {
        Car leftCar = null;
        Car rightCar = null;
        return "redirect:/cardGameView";
    }

    @GetMapping("/cardGameView")
    public String showView(Model model) {
        model.addAttribute("leftCar", leftCar);
        model.addAttribute("rightCar", rightCar);
        model.addAttribute("numberLeft", leftCarList.size());
        model.addAttribute("numberRight", rightCarList.size());
        return "cardGameForm";
    }

    @PostMapping(value = "/cardGameView", params = {"showButton=showLeft"})
    public String showLeftCard(Model model) {
        if(leftCarList.isEmpty()){
            leftCar = null;
        }else{
            leftCar = leftCarList.get(0);
        }
        model.addAttribute("leftCar", leftCar);
        model.addAttribute("rightCar", rightCar);
        model.addAttribute("numberLeft", leftCarList.size());
        model.addAttribute("numberRight", rightCarList.size());
        return "cardGameForm";
    }

    @PostMapping(value = "/cardGameView", params = {"moveButton=moveToLeft"})
    public String moveCardToLeft(Model model) {
        if(rightCarList.isEmpty()){
            //do nothing
        }else{
            //Verliererkarte hinzufügen
            leftCarList.add(rightCarList.remove(0));
            //Siegerkarte nach hinten schieben
            leftCarList.add(leftCarList.remove(0));
        }
        leftCar = null;
        rightCar = null;
        model.addAttribute("leftCar", leftCar);
        model.addAttribute("rightCar", rightCar);
        model.addAttribute("numberLeft", leftCarList.size());
        model.addAttribute("numberRight", rightCarList.size());
        return "cardGameForm";
    }

    @PostMapping(value = "/cardGameView", params = {"showButton=showRight"})
    public String showRightCard(Model model) {
        if(rightCarList.isEmpty()){
            rightCar = null;
        }else{
            rightCar = rightCarList.get(0);
        }
        model.addAttribute("leftCar", leftCar);
        model.addAttribute("rightCar", rightCar);
        model.addAttribute("numberLeft", leftCarList.size());
        model.addAttribute("numberRight", rightCarList.size());
        return "cardGameForm";
    }

    @PostMapping(value = "/cardGameView", params = {"moveButton=moveToRight"})
    public String moveCardToRight(Model model) {
        if (leftCarList.isEmpty()) {
            //do nothing
        } else {
            //Verliererkarte hinzufügen
            rightCarList.add(leftCarList.remove(0));
            //Siegerkarte nach hinten schieben
            rightCarList.add(rightCarList.remove(0));
        }
        leftCar = null;
        rightCar = null;
        model.addAttribute("leftCar", leftCar);
        model.addAttribute("rightCar", rightCar);
        model.addAttribute("numberLeft", leftCarList.size());
        model.addAttribute("numberRight", rightCarList.size());
        return "cardGameForm";
    }

    @PostMapping(value = "/cardGameView", params = {"showButton=reset"})
    public String resetView(Model model) {
        setup();
        leftCar = null;
        rightCar = null;
        model.addAttribute("leftCar", leftCar);
        model.addAttribute("rightCar", rightCar);
        model.addAttribute("numberLeft", leftCarList.size());
        model.addAttribute("numberRight", rightCarList.size());
        return "cardGameForm";
    }

}

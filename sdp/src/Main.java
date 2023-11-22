import java.util.ArrayList;
import java.util.List;

// Singleton Pattern
class AituNET {
    private static final AituNET instance = new AituNET();

    private AituNET() {
    }

    public static AituNET getInstance() {
        return instance;
    }
}

// Strategy Pattern
interface SkillStrategy {
    void performSkill();
}

class ProgrammingSkill implements SkillStrategy {
    @Override
    public void performSkill() {
        System.out.println("Performing programming skill");
    }
}

class DesignSkill implements SkillStrategy {
    @Override
    public void performSkill() {
        System.out.println("Performing design skill");
    }
}

// Decorator Pattern
interface SkillDecorator extends SkillStrategy {
}

class AdvancedProgrammingSkill implements SkillDecorator {
    private final SkillStrategy baseSkill;

    public AdvancedProgrammingSkill(SkillStrategy baseSkill) {
        this.baseSkill = baseSkill;
    }

    @Override
    public void performSkill() {
        System.out.println("Performing advanced programming skill");
        baseSkill.performSkill();
    }
}

// Adapter Pattern
interface LegacySkill {
    void executeLegacySkill();
}

class LegacySkillAdapter implements SkillStrategy {
    private final LegacySkill legacySkill;

    public LegacySkillAdapter(LegacySkill legacySkill) {
        this.legacySkill = legacySkill;
    }

    @Override
    public void performSkill() {
        legacySkill.executeLegacySkill();
    }
}

// Observer Pattern
interface Observer {
    void update();
}

class ConcreteObserver implements Observer {
    @Override
    public void update() {
        System.out.println("Observer updated");
    }
}

interface Observable {
    void addObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers();
}

class ConcreteObservable implements Observable {
    private final List<Observer> observers = new ArrayList<>();

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(Observer::update);
    }
}

// Factory Method Pattern
interface SkillFactory {
    SkillStrategy createSkill();
}

class ProgrammingSkillFactory implements SkillFactory {
    @Override
    public SkillStrategy createSkill() {
        return new ProgrammingSkill();
    }
}

class DesignSkillFactory implements SkillFactory {
    @Override
    public SkillStrategy createSkill() {
        return new DesignSkill();
    }
}

public class Main {
    public static void main(String[] args) {
        AituNET aituNET = AituNET.getInstance();

        SkillFactory programmingSkillFactory = new ProgrammingSkillFactory();
        SkillStrategy programmingSkill = programmingSkillFactory.createSkill();
        programmingSkill.performSkill();

        SkillFactory designSkillFactory = new DesignSkillFactory();
        SkillStrategy designSkill = designSkillFactory.createSkill();
        designSkill.performSkill();

        SkillDecorator advancedProgrammingSkill = new AdvancedProgrammingSkill(programmingSkill);
        advancedProgrammingSkill.performSkill();

        LegacySkill legacySkill = () -> System.out.println("Executing legacy skill");
        SkillStrategy legacySkillAdapter = new LegacySkillAdapter(legacySkill);
        legacySkillAdapter.performSkill();

        Observer observer = new ConcreteObserver();
        Observable observable = new ConcreteObservable();
        observable.addObserver(observer);

        observable.notifyObservers();
    }
}

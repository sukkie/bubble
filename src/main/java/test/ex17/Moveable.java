package test.ex17;

public interface Moveable {
    public abstract void up();
//    public abstract void down();
    public abstract void left();
    public abstract void right();
    // default를 사용하면 인터페이스도 몸체 있는 메소드를 만들 수 있다.(다종 상속이 안되기 때문에 어댑터 패턴보다는 default를 사용)
    default public void down() {};
    default public void attack() {}
}

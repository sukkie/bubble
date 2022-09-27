package test.ex11;

// 어댑터패턴 => 걸러내는 역할
// Bubble에서 다중 상속할 수 없으므로 미사용
@Deprecated
public abstract class MoveAdapter implements Moveable {

    @Override
    public void down() {
    }
}

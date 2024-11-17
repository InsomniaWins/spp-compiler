package wins.insomnia.sppcompiler.parse.literal;

import wins.insomnia.sppcompiler.parse.misc.Expression;

public class Literal<T> extends Expression {

    private final T VALUE;

    public Literal(T value) {
        this.VALUE = value;
    }

    public T getValue() {
        return VALUE;
    }

    @Override
    public String toString() {
        return "Literal<" + VALUE.getClass().getSimpleName() + "> : {" + String.valueOf(VALUE) + "}";
    }

}

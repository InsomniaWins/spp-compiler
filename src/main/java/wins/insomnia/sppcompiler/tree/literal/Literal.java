package wins.insomnia.sppcompiler.tree.literal;

import wins.insomnia.sppcompiler.tree.expression.Expression;

public class Literal<T> extends Expression {

    private T value;

    public Literal(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T newValue) {
        value = newValue;
    }

    @Override
    public String toString() {
        return "Literal<" + value.getClass().getSimpleName() + "> : {" + String.valueOf(value) + "}";
    }

}

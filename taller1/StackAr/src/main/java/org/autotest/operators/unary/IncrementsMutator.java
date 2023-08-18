package org.autotest.operators.unary;

import org.autotest.helpers.BinaryOperatorKindToString;
import org.autotest.helpers.UnaryOperatorKindToString;
import org.autotest.operators.MutationOperator;
import spoon.reflect.code.*;
import spoon.reflect.declaration.CtElement;

import java.util.Arrays;
import java.util.List;

/**
 * Operador de mutación basado en https://pitest.org/quickstart/mutators/#INCREMENTS
 *
 * Este operador de mutación reemplaza los operadores de incremento y decremento de variables locales (variables de pila).
 */
public class IncrementsMutator extends MutationOperator {
    @Override
    public boolean isToBeProcessed(CtElement candidate) {
        if(!(candidate instanceof CtUnaryOperator)) {
            return false;
        }

        CtUnaryOperator op = (CtUnaryOperator) candidate;

        List<UnaryOperatorKind> incOrDecList = Arrays.asList(
                UnaryOperatorKind.POSTDEC,
                UnaryOperatorKind.POSTINC,
                UnaryOperatorKind.PREDEC,
                UnaryOperatorKind.PREINC
        );

        // Chequear que sea incrementacion o decrementacion
        return incOrDecList.contains(op.getKind());
    }

    @Override
    public void process(CtElement candidate) {
        CtUnaryOperator op = (CtUnaryOperator) candidate;
        op.setKind(getReplacement(op.getKind()));
    }

    public UnaryOperatorKind getReplacement(UnaryOperatorKind kind) {
        switch (kind) {
            case POSTDEC:
                return UnaryOperatorKind.POSTINC; // x-- -> x++
            case POSTINC:
                return UnaryOperatorKind.POSTDEC; // x++ -> x--
            case PREDEC:
                return UnaryOperatorKind.PREINC; // --x -> ++x
            case PREINC:
                return UnaryOperatorKind.PREDEC; // ++x -> --x
            default:
                return null;
        }
    }

    @Override
    public String describeMutation(CtElement candidate) {
        CtUnaryOperator op = (CtUnaryOperator) candidate;
        return this.getClass().getSimpleName() + ": Se reemplazó " +
                getReplacementString(op, UnaryOperatorKindToString.get(op.getKind())) +  " por " +
                getReplacementString(op, UnaryOperatorKindToString.get(getReplacement(op.getKind()))) +
                " en la línea " + op.getPosition().getLine() + ".";
    }

    public String getReplacementString(CtUnaryOperator op, String replacement) {
        CtExpression variable = op.getOperand();
        switch (op.getKind()) {
            case POSTINC:
            case POSTDEC:
                return variable.toString() + replacement;
            case PREDEC:
            case PREINC:
                return replacement + variable.toString();
            default:
                return null;
        }
    }
}

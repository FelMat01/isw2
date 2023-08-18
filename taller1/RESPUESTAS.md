# Respuestas

## Ejercicio 1

a. ¿Cuantos mutantes se generaron en total?

En total, se generaron 72 mutantes, que se separan entre los siguientes tipos de mutantes: 
- TrueConditionalsMutator: 10
- FalseConditionalsMutator: 10
- NegateConditionsMutator: 9
- OneConstantMutator: 6
- MinusOneConstantMutator: 6
- TrueReturnsMutator: 6
- ZeroConstantMutator: 5
- MathMutator: 5
- FalseReturnsMutator: 4
- EmptyReturnsMutator: 3
- ConditionalsBoundaryMutator: 3
- IncrementsMutator: 3
- NullReturnsMutator: 2

b ¿Que operador de mutación genero mas mutantes? ¿Cuantos y por que?

TrueConditional y FalseConditional fueron los más generados. Esto es porque hay muchos Ifs en el código de StackAr.


c ¿Que operador de mutación genero menos mutantes? ¿Cuantos y por que?

NullReturn es el menos generado. Esto es porque hay muy pocas funciones que retornen objetos en vez de primitivas, y la mayoría simplemente son funciones void.

## Ejercicio 2

a ¿Cuantos mutantes vivos y muertos encontraron cada uno de los test suites?
b ¿Cual es el mutation score de cada test suite?


En el test suite StackTests1 hay 18 mutantes muertos y 54 mutantes vivos de un total de 72 mutantes. El mutation score en este caso es de 25%

En el test suite StackTests2 hay 35 muertos y 37 mutantes vivos. El mutation score de este test suite es de 48%, mejor que el test suite anterior.

Que StackTests2 tenga un mejor mutation score que StackTests1 puede deberse por el hecho de que StackTests2 tenga los mismos tests que StackTests1 y otros tests mas, generando
mas oportunidades de generar y encontrar fallas.

## Ejercicio 3
a. ¿Cual es el mutation score logrado para los tests de StackTests3?

El mutation score logrado es de 93%

b. ¿Cuantos mutantes vivos y muertos encontraron?

Encontramos 5 mutantes vivos, que son equivalentes (Ver c), y 67 mutantes muertos.

c. Comente cuales son todos los mutantes vivos que quedaron y porque son equivalentes al programa original (si no lo fueran, todavia es posible mejorar el mutation score).

Encontramos los siguientes mutantes equivalentes:

StackArMutated3971 (MathMutator: Se reemplazó * por / en la línea 65.)
- Equivalente porque multiplicar o dividir por 1 es lo mismo, y eso es lo que esta haciendo.

StackArMutated7533 (FalseConditionalsMutator: Se reemplazó isEmpty() por False en la línea 45.)
- Equivalente porque, aunque no falle en el pop, se hace un top despues con la misma condicion que reemplazo y falla ahi.

StackArMutated200 (FalseConditionalsMutator: Se reemplazó this == obj por False en la línea 72.)
- Equivalente porque, para que la mutante muera tenemos que pasar `equals` con el objeto a si mismo y retorne que son distintos, pero si no son iguales cuando pasan por ese if (siempre es false), el resultado termina siendo true porque si bien no son el mismo objeto, si son iguales, entonces pasa por las otras condiciones sin ser detectada la infeccion.

StackArMutated7144 (TrueReturnsMutator: Se reemplazó false por true en la línea 82.)
StackArMutated6910 (FalseConditionalsMutator: Se reemplazó readIndex != other.readIndex por False en la línea 81.)

Veamos el siguiente codigo donde se produce la mutacion
```
1.     if (!Arrays.equals(elems, other.elems))
2.         return false;
3.     if (readIndex != other.readIndex)
4.         return false;
```

Se tiene que no se puede cumplir lo de la linea 81 sin que se cumpla tambien lo de la linea 79. Es decir, no se puede tener elementos iguales (y que pase al siguiente if) pero readIndex distinto, eso es algo que se mantiene en el stack como un invariante.

Por lo tanto, cualquier test que intente chequear el valor interno de dos stacks con readIndex distinto nunca va a llegar a los defectos de las lineas 81 y 82, nunca produciendo una infeccion y por lo tanto nunca produciendo una falla.

d. ¿Cual es el instruction coverage promedio que lograron para las clases mutadas? Puede encontrar este valor al final del reporte de JaCoCo para el paquete org.autotest.mutants (la ultima fila da el Total).

El instruction coverage promedio logrado para las clases mutadas es de un 58%.

e. ¿Cual es el peor instruction coverage que lograron para una clase mutada? ¿Por que creen que sucede esto?

El peor instruction coverage es para la clase mutada con la siguiente mutacion:
- (TrueConditionalsMutator: Se reemplazó capacity < 0 por true en la línea 18.)

Esta mutacion hace que cualquier creacion de un stack termine siempre tirando una excepcion, por lo que no puede seguir con las otras lineas de codigo ya que se traba siempre ahi.

# Kotlin DI Ejemplos
Servicio web para API REST con Kotlin y Ktor.  

[![Kotlin](https://img.shields.io/badge/Code-Kotlin-blueviolet)](https://kotlinlang.org/)
[![LISENCE](https://img.shields.io/badge/Lisence-MIT-green)]()
![GitHub](https://img.shields.io/github/last-commit/joseluisgs/Kotlin-DI-Ejemplos)


![imagen](https://www.adesso-mobile.de/wp-content/uploads/2021/02/kotlin-einfu%CC%88hrung.jpg)

## Acerca de
El siguiente proyecto tiene como objetivo acercar cómo usar la Inyección de Dependencias en Kotlin
ya sea de manera manual o usando librerías como Dagger y Koin.

## Inyección de Dependencias (DI)

La inyección de dependencias es una técnica de desarrollo que permite a los desarrolladores de software, a través de la inyección de dependencias, obtener una dependencia de una clase en una clase que no tiene acceso a ella.

El Principio de inyección de dependencia no es más que poder pasar (inyectar) las dependencias cuando sea necesario en lugar de inicializar las dependencias dentro de la clase receptora y con ello poder desacoplar la construcción de sus clases de la construcción de las dependencias de sus clases.

Es decir, aplicamos una composición entre clases, con el objetivo que cada clase tenga sus responsabilidades bien definidas y acotadas. Es decir, si una clase A, necesita alguna funcionalidad de B, nosotros al crear A, debemos "inyectarle" B. De esta manera A, puede usar la funcionalidad de B. 

De esta manera, podemos cambiar B, por C, siempre y cuando mantengan el contrato que permite ser usado por A. Ya no es la clase A la responsable de definir sus dependencias sino que lo es el programa o clase superior que le inyecta la dependencia que en ese momento necesite según los requerimientos.

### Código Acoplado
Esto es lo que **no deberíamos hacer**
```kotlin
class ClassA {

  var classB = ClassB();

  fun tenPercent() {
    return classB.calculate() * 0.1d;
  }
}
```
```kotlin
fun main() {
    val classA = ClassA();# Inyección de Dependencias (DI)

La inyección de dependencias es una técnica de desarrollo que permite a los desarrolladores de software, a través de la inyección de dependencias, obtener una dependencia de una clase en una clase que no tiene acceso a ella.

El Principio de inyección de dependencia no es más que poder pasar (inyectar) las dependencias cuando sea necesario en lugar de inicializar las dependencias dentro de la clase receptora y con ello poder desacoplar la construcción de sus clases de la construcción de las dependencias de sus clases.

Es decir, aplicamos una composición entre clases, con el objetivo que cada clase tenga sus responsabilidades bien definidas y acotadas. Es decir, si una clase A, necesita alguna funcionalidad de B, nosotros al crear A, debemos "inyectarle" B. De esta manera A, puede usar la funcionalidad de B. 

De esta manera, podemos cambiar B, por C, siempre y cuando mantengan el contrato que permite ser usado por A. Ya no es la clase A la responsable de definir sus dependencias sino que lo es el programa o clase superior que le inyecta la dependencia que en ese momento necesite según los requerimientos.

## Código Acoplado
Esto es lo que **no deberíamos hacer**
```java
class ClassA {

  ClassB classB = new ClassB();

  int tenPercent() {
    return classB.calculate() * 0.1d;
  }
}
```
```java
class Main {
  public static void main(String... args) {
    ClassA classA = new ClassA();

    System.out.println("Ten Percent: " + classA.tenPercent());
  }
}
```

## Inyección por Setter
No recomendado. Con este enfoque, eliminamos la palabra clave new ClassB de nuestra ClassA. Por lo tanto, alejamos la responsabilidad de la creación de ClassB deClassA.

```java
class ClassA {

  ClassB classB;

  /* Setter Injection */
  void setClassB(ClassB injected) {
    classB = injected;
  }

  int tenPercent() {
    return classB.calculate() * 0.1d;
  }
}
```
```java
class Main {
  public static void main(String... args) {
    ClassA classA = new ClassA();
    ClassB classB = new ClassB();

    classA.setClassB(classB);

    System.out.println("Ten Percent: " + classA.tenPercent());
  }
}
```

Pero hay un problema significativo con el enfoque de Inyección con Setters:

Estamos ocultando la dependencia ClassB enClassA porque al leer la firma del constructor, no podemos identificar sus dependencias de inmediato. El siguiente código provoca una NullPointerException en tiempo de ejecución:
```java
class Main {
  public static void main(String... args) {
    ClassA classA = new ClassA();

    System.out.println("Ten Percent: " + classA.tenPercent()); // NullPointerException here
  }
}
```

## Inyección con Constructor
ClassA todavía tiene una fuerte dependencia de ClassB pero ahora se puede inyectar desde afuera usando el constructor:

```java
class ClassA {

  ClassB classB;

  /* Constructor Injection */
  ClassA(ClassB injected) {
    classB = injected;
  }

  int tenPercent() {
    return classB.calculate() * 0.1d;
  }
}
```
```java
class Main {
  public static void main(String... args) {
    /* Notice that we are creating ClassB fisrt */
    ClassB classB = new ClassB();

    /* Constructor Injection */
    ClassA classA = new ClassA(classB);

    System.out.println("Ten Percent: " + classA.tenPercent());
  }
}
```

La funcionalidad permanece intacta en comparación con el enfoque de Inyección Setter. Eliminamos la inicialización nueva de la ClaseA.

Todavía podemos inyectar una subclase especializada de ClassB a ClassA.

Ahora el compilador nos pedirá las dependencias que necesitamos en tiempo de compilación.



    println("Ten Percent: ${classA.tenPercent()}");
  }
}
```

## Inyección por Setter
No recomendado. Con este enfoque, eliminamos la palabra clave new ClassB de nuestra ClassA. Por lo tanto, alejamos la responsabilidad de la creación de ClassB deClassA.

```java
class ClassA {

  ClassB classB;

  /* Setter Injection */
  void setClassB(ClassB injected) {
    classB = injected;
  }

  int tenPercent() {
    return classB.calculate() * 0.1d;
  }
}
```
```java
class Main {
  public static void main(String... args) {
    ClassA classA = new ClassA();
    ClassB classB = new ClassB();

    classA.setClassB(classB);

    System.out.println("Ten Percent: " + classA.tenPercent());
  }
}
```

Pero hay un problema significativo con el enfoque de Inyección con Setters:

Estamos ocultando la dependencia ClassB enClassA porque al leer la firma del constructor, no podemos identificar sus dependencias de inmediato. El siguiente código provoca una NullPointerException en tiempo de ejecución:
```java
class Main {
  public static void main(String... args) {
    ClassA classA = new ClassA();

    System.out.println("Ten Percent: " + classA.tenPercent()); // NullPointerException here
  }
}
```

## Inyección con Constructor
ClassA todavía tiene una fuerte dependencia de ClassB pero ahora se puede inyectar desde afuera usando el constructor:

```java
class ClassA {

  ClassB classB;

  /* Constructor Injection */
  ClassA(ClassB injected) {
    classB = injected;
  }

  int tenPercent() {
    return classB.calculate() * 0.1d;
  }
}
```
```java
class Main {
  public static void main(String... args) {
    /* Notice that we are creating ClassB fisrt */
    ClassB classB = new ClassB();

    /* Constructor Injection */
    ClassA classA = new ClassA(classB);

    System.out.println("Ten Percent: " + classA.tenPercent());
  }
}
```

La funcionalidad permanece intacta en comparación con el enfoque de Inyección Setter. Eliminamos la inicialización nueva de la ClaseA.

Todavía podemos inyectar una subclase especializada de ClassB a ClassA.

Ahora el compilador nos pedirá las dependencias que necesitamos en tiempo de compilación.



## Autor

Codificado con :sparkling_heart: por [José Luis González Sánchez](https://twitter.com/joseluisgonsan)

[![Twitter](https://img.shields.io/twitter/follow/joseluisgonsan?style=social)](https://twitter.com/joseluisgonsan)
[![GitHub](https://img.shields.io/github/followers/joseluisgs?style=social)](https://github.com/joseluisgs)

### Contacto
<p>
  Cualquier cosa que necesites házmelo saber por si puedo ayudarte 💬.
</p>
<p>
    <a href="https://twitter.com/joseluisgonsan" target="_blank">
        <img src="https://i.imgur.com/U4Uiaef.png" 
    height="30">
    </a> &nbsp;&nbsp;
    <a href="https://github.com/joseluisgs" target="_blank">
        <img src="https://distreau.com/github.svg" 
    height="30">
    </a> &nbsp;&nbsp;
    <a href="https://www.linkedin.com/in/joseluisgonsan" target="_blank">
        <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/c/ca/LinkedIn_logo_initials.png/768px-LinkedIn_logo_initials.png" 
    height="30">
    </a>  &nbsp;&nbsp;
    <a href="https://joseluisgs.github.io/" target="_blank">
        <img src="https://joseluisgs.github.io/favicon.png" 
    height="30">
    </a>
</p>


## Licencia

Este proyecto está licenciado bajo licencia **MIT**, si desea saber más, visite el fichero [LICENSE](./LICENSE) para su uso docente y educativo.
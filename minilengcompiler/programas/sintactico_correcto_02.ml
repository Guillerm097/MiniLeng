programa correcto2;

entero n;

accion no_hacer_nada;
    booleano nada;

    principio
        nada := true;

        si nada = true ent
            escribir("No hago nada");
        si_no
            escribir("No hacer nada implica hacer algo");
        fsi
    fin

principio
    n := 100;
    mq n <> 0
        n := n - 1;
    fmq
fin
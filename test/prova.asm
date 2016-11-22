.def f: args=0 ,locals=3
iconst 5
iconst 4
iconst 2
imul
iadd
ret
.def main:args=0 ,locals=0
call f()
print
halt


//funzione che fa somme a caso
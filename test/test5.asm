.def main: args=0 ,locals=0
call funzione()
print
call stampante()
print
halt
.def funzione: args=0 ,locals=0
iconst 5
itof
fconst 0.5
fadd
ret
.def stampante: args=0 ,locals=0
sconst "sono diventato un float"
ret
.globals 0
.def main: args= 0 , locals=0
sconst "Start"
call stampa()
call metti123SulloStack()
call doppiaSomma()
call stampa()
call fine()
halt
.def stampa: args= 0 , locals=0
print
ret
.def doppiaSomma: args= 3 ,locals=0
iadd
iadd
ret
.def metti123SulloStack: args= 0 , locals=0
iconst 1
iconst 2
iconst 3
ret
.def fine: args= 0 ,locals=0
sconst "Fine"
print 
ret
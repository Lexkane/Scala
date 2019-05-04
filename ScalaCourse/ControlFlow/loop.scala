import util.control.Breaks._
//for (item<-iterable_sequence){
	//do logic
//}


for (item <- List(1,2,3)){
	println("Hello")
}

for (num<- List(1,2,3)){
	println(num)
}


for (num<-Range(0,10)){
	if (num%2==0){
		println(s"$num is even")
	} else{
		println(s"$num is odd")
	}
}

val names =List("John","Abe","Cindy","Cat")

for (name <-names){
	if (name.startsWith("C"){
		println(s"$name starts with C")
	}
}

var x=0

while (x<5){
	println(s"x is currently $x")
	println("x is still less than 5, adding 1 to x")
	x=x+1
}

var y=0

while( y<10){
	println(s"y is currently $y")
	println("y is still less than 10, add 1 to y")
	y=y+1
	if (y==3) break
}
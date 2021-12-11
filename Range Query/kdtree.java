import java.util.*;
import java.io.*;
class TreeNode{
              public int leaf;
              public int longmax;
              public int longmin; 
              public int latmax;
              public int latmin;
              public TreeNode right;
              public TreeNode left;
              public TreeNode parent;
              public ArrayList<Integer> latarr;
              public ArrayList<Integer> longarr;
              public int spin;
}
public class kdtree{
public static void main(String[] args){

ArrayList<Integer> rlongi = new ArrayList<Integer>();
ArrayList<Integer> rlati = new ArrayList<Integer>();
ArrayList<Integer> qlongi = new ArrayList<Integer>();
ArrayList<Integer> qlati = new ArrayList<Integer>();
try{
FileInputStream fstream = new FileInputStream("restaurants.txt");
Scanner s = new Scanner(fstream);
String temp=s.nextLine();
while(s.hasNextLine()){
String p = s.nextLine();
int i =0;
boolean f = true;
while(i<p.length() && f==true){
if(p.charAt(i) == ','){
f=false;
}else{
i++;
}}
int sr = Integer.parseInt(p.substring(0,i));
int r = Integer.parseInt(p.substring(i+1,p.length()));
rlati.add(sr);
rlongi.add(r);
}} catch (FileNotFoundException e ){
System.out.println("File not found");
}
try{
FileInputStream fstream = new FileInputStream("queries.txt");
Scanner s = new Scanner(fstream);
String temp=s.nextLine();
while(s.hasNextLine()){
String p = s.nextLine();
int i =0;
boolean f = true;
while(i<p.length() && f==true){
if(p.charAt(i) == ','){
f=false;
}else{
i++;
}}
int sr = Integer.parseInt(p.substring(0,i));
int r = Integer.parseInt(p.substring(i+1,p.length()));
qlati.add(sr);
qlongi.add(r);
}} catch (FileNotFoundException e ){
System.out.println("File not found");
}
int n = rlongi.size();
TreeNode t = new TreeNode();
t.leaf = n;
t.spin = 1;
t.longmax = 1000000;
t.longmin = -1000000;
t.latmax = 1000000;
t.latmin = -1000000;
t.latarr = rlati;
t.longarr = rlongi;
build(t);
ArrayList<Integer> out = new ArrayList<Integer>();
for(int i=0;i<qlati.size();i++){
out.add(count(t,qlati.get(i),qlongi.get(i)));
}
try{
FileOutputStream fs = new FileOutputStream("output.txt",true);
PrintStream p = new PrintStream(fs);
int i =0;
while(i<out.size()){
System.out.println(out.get(i));
p.print(out.get(i) + ",");
i++;
}
} catch (FileNotFoundException e1){
System.out.println(" File not found");
}
}


public static void build(TreeNode t){
if(t.leaf ==1){
}else if(t.leaf %2 ==0){
TreeNode t1 = new TreeNode();
TreeNode t2 = new TreeNode();
t1.parent = t;
t2.parent =t;
t.left = t1;
t.right = t2;
t1.leaf = t.leaf/2;
t2.leaf = t.leaf/2;
if(t.spin ==1){
t1.spin =0;
t2.spin =0;
t1.longmax = t.longmax;
t2.longmax = t.longmax;
t1.longmin = t.longmin;
t2.longmin = t.longmin;
t1.latmin = t.latmin;
t2.latmax = t.latmax;
ArrayList<Integer> d= new ArrayList<Integer>();
ArrayList<Integer> e= new ArrayList<Integer>();
ArrayList<Integer> g= sorted(t.latarr);
if(g.size()% 2==0){
for(int i = 0;i<t.latarr.size();i++){
if(t.latarr.get(i)<=g.get((g.size()/2)-1)){
t1.latmax =g.get((g.size()/2)-1);
d.add(t.longarr.get(i));
e.add(t.latarr.get(i));
}}}else{
for(int i = 0;i<t.latarr.size();i++){
if(t.latarr.get(i)<=g.get(g.size()/2)){
t1.latmax = g.get(g.size()/2);
d.add(t.longarr.get(i));
e.add(t.latarr.get(i));
}}}
t1.latarr = e;
t1.longarr =d;
ArrayList<Integer> h= new ArrayList<Integer>();
ArrayList<Integer> f= new ArrayList<Integer>();
ArrayList<Integer> z= sorted(t.latarr);
if(z.size()% 2==0){
for(int i = 0;i<t.latarr.size();i++){
if(t.latarr.get(i)>z.get((z.size()/2)-1)){
t2.latmin =(z.get((z.size()/2)-1))+1;
h.add(t.longarr.get(i));
f.add(t.latarr.get(i));
}}}else{
for(int i = 0;i<t.latarr.size();i++){
if(t.latarr.get(i)>z.get(z.size()/2)){
t2.latmin = (z.get(z.size()/2))+1;
h.add(t.longarr.get(i));
f.add(t.latarr.get(i));
}}}
t2.latarr = f;
t2.longarr =h;
}else{
t1.spin =1;
t2.spin =1;
t1.latmax = t.latmax;
t2.latmax = t.latmax;
t1.latmin = t.latmin;
t2.latmin = t.latmin;
t1.longmin = t.longmin;
t2.longmax = t.longmax;
ArrayList<Integer> d= new ArrayList<Integer>();
ArrayList<Integer> e= new ArrayList<Integer>();
ArrayList<Integer> g= sorted(t.longarr);
if(g.size()% 2==0){
for(int i = 0;i<t.longarr.size();i++){
if(t.longarr.get(i)<=g.get((g.size()/2)-1)){
t1.longmax =g.get((g.size()/2)-1);
d.add(t.longarr.get(i));
e.add(t.latarr.get(i));
}}}else{
for(int i = 0;i<t.longarr.size();i++){
if(t.longarr.get(i)<=g.get(g.size()/2)){
t1.longmax = g.get(g.size()/2);
d.add(t.longarr.get(i));
e.add(t.latarr.get(i));
}}}
t1.latarr = e;
t1.longarr =d;
ArrayList<Integer> h= new ArrayList<Integer>();
ArrayList<Integer> f= new ArrayList<Integer>();
ArrayList<Integer> z= sorted(t.longarr);
if(z.size()% 2==0){
for(int i = 0;i<t.longarr.size();i++){
if(t.longarr.get(i)>z.get((z.size()/2)-1)){
t2.longmin =(z.get((z.size()/2)-1))+1;
h.add(t.longarr.get(i));
f.add(t.latarr.get(i));
}}}else{
for(int i = 0;i<t.longarr.size();i++){
if(t.longarr.get(i)>z.get(z.size()/2)){
t2.longmin = (z.get(z.size()/2))+1;
h.add(t.longarr.get(i));
f.add(t.latarr.get(i));
}}}
t2.latarr = f;
t2.longarr =h;
}
build(t1);
build(t2);
}else{
TreeNode t1 = new TreeNode();
TreeNode t2 = new TreeNode();
t1.parent = t;
t2.parent =t;
t.left = t1;
t.right = t2;
t1.leaf = (t.leaf+1)/2;
t2.leaf =(int) t.leaf/2;
if(t.spin ==1){
t1.spin =0;
t2.spin =0;
t1.longmax = t.longmax;
t2.longmax = t.longmax;
t1.longmin = t.longmin;
t2.longmin = t.longmin;
t1.latmin = t.latmin;
t2.latmax = t.latmax;
ArrayList<Integer> d= new ArrayList<Integer>();
ArrayList<Integer> e= new ArrayList<Integer>();
ArrayList<Integer> g= sorted(t.latarr);
if(g.size()% 2==0){
for(int i = 0;i<t.latarr.size();i++){
if(t.latarr.get(i)<=g.get((g.size()/2)-1)){
t1.latmax =g.get((g.size()/2)-1);
d.add(t.longarr.get(i));
e.add(t.latarr.get(i));
}}}else{
for(int i = 0;i<t.latarr.size();i++){
if(t.latarr.get(i)<=g.get(g.size()/2)){
t1.latmax = g.get(g.size()/2);
d.add(t.longarr.get(i));
e.add(t.latarr.get(i));
}}}
t1.latarr = e;
t1.longarr =d;
ArrayList<Integer> h= new ArrayList<Integer>();
ArrayList<Integer> f= new ArrayList<Integer>();
ArrayList<Integer> z= sorted(t.latarr);
if(z.size()% 2==0){
for(int i = 0;i<t.latarr.size();i++){
if(t.latarr.get(i)>z.get((z.size()/2)-1)){
t2.latmin =(z.get((z.size()/2)-1))+1;
h.add(t.longarr.get(i));
f.add(t.latarr.get(i));
}}}else{
for(int i = 0;i<t.latarr.size();i++){
if(t.latarr.get(i)>z.get(z.size()/2)){
t2.latmin = (z.get(z.size()/2))+1;
h.add(t.longarr.get(i));
f.add(t.latarr.get(i));
}}}
t2.latarr = f;
t2.longarr =h;
}else{
t1.spin =1;
t2.spin =1;
t1.latmax = t.latmax;
t2.latmax = t.latmax;
t1.latmin = t.latmin;
t2.latmin = t.latmin;
t1.longmin = t.longmin;
t2.longmax = t.longmax;
ArrayList<Integer> d= new ArrayList<Integer>();
ArrayList<Integer> e= new ArrayList<Integer>();
ArrayList<Integer> g= sorted(t.longarr);
if(g.size()% 2==0){
for(int i = 0;i<t.longarr.size();i++){
if(t.longarr.get(i)<=g.get((g.size()/2)-1)){
t1.longmax =g.get((g.size()/2)-1);
d.add(t.longarr.get(i));
e.add(t.latarr.get(i));
}}}else{
for(int i = 0;i<t.longarr.size();i++){
if(t.longarr.get(i)<=g.get(g.size()/2)){
t1.longmax = g.get(g.size()/2);
d.add(t.longarr.get(i));
e.add(t.latarr.get(i));
}}}
t1.latarr = e;
t1.longarr =d;
ArrayList<Integer> h= new ArrayList<Integer>();
ArrayList<Integer> f= new ArrayList<Integer>();
ArrayList<Integer> z= sorted(t.longarr);
if(z.size()% 2==0){
for(int i = 0;i<t.longarr.size();i++){
if(t.longarr.get(i)>z.get((z.size()/2)-1)){
t2.longmin =(z.get((z.size()/2)-1))+1;
h.add(t.longarr.get(i));
f.add(t.latarr.get(i));
}}}else{
for(int i = 0;i<t.longarr.size();i++){
if(t.longarr.get(i)>z.get(z.size()/2)){
t2.longmin = (z.get(z.size()/2))+1;
h.add(t.longarr.get(i));
f.add(t.latarr.get(i));
}}}
t2.latarr = f;
t2.longarr =h;
}
build(t1);
build(t2);
}}
public static ArrayList<Integer> sorted(ArrayList<Integer> A){
if(A.size() ==0){
return A;
}else if(A.size() ==1){
return A;
}else{
Collections.sort(A);
return A;
}}
public static int count(TreeNode t,int x,int y){
if(t.leaf ==0){
return 0;
}else if(t.leaf ==1){
if(t.longarr.get(0)<=100+y && t.longarr.get(0)>=y-100 && t.latarr.get(0)<=100+x && t.latarr.get(0)>=x-100){
return 1;
}else{
return 0;
}}
else{ 
if((t.longmax <y-100) ||  (t.latmax <x-100) || (t.latmin >x+100) || (t.longmin >y+100)){
return 0;
}else{
return count(t.left,x,y)+count(t.right,x,y);
}}}}





using System;
namespace ToyLanguage.Model
{
    public class MyTuple<T1, T2>
    {
        private T1 firtst;
        private T2 second;
        public MyTuple(T1 first, T2 second)
        {
            this.firtst = first;
            this.second = second;
        }
        public T1 getFirst()
        {
            return this.firtst;
        }
        public T2 getSecond()
        {
            return this.second;
        }
        public override string ToString()
        {
            return "< " + this.firtst.ToString() + ", " + this.second.ToString() + " >";
        }
    }
}

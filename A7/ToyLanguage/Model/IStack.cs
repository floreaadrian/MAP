using System;
namespace ToyLanguage.Model
{
    public interface IStack<T>
    {
        T pop();
        void push(T v);
        bool isEmpty();
        void clear();
    }
}

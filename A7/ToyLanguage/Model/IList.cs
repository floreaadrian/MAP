using System;
namespace ToyLanguage.Model
{
    public interface IList<T>
    {
        void add(T var);
        T fromIndex(int index);
        int size();
        void clear();
    }
}

# Zapisy na kurs

## Dlaczego przy SeekBar nie można było użyć lambdy, a przy CheckBox można?

Przy CheckBox można użyć lambdy, ponieważ interfejs `CompoundButton.OnCheckedChangeListener` posiada tylko jedną metodę – `onCheckedChanged()`.

Natomiast `SeekBar.OnSeekBarChangeListener` posiada trzy metody:
- `onProgressChanged()`,
- `onStartTrackingTouch()`,
- `onStopTrackingTouch()`.

Lambda może być używana tylko wtedy, gdy interfejs ma dokładnie jedną metodę abstrakcyjną. Dlatego przy SeekBar trzeba zastosować klasę anonimową i zaimplementować wszystkie trzy metody.

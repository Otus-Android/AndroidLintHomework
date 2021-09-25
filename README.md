## Напишите следующие правила для Android Lint:

1. Если используется GlobalScope сообщите об инциденте. `Severity.WARNING`
    * Если мы находимся внутри класса-наследника ViewModel и в класспасе есть
      экстеншен `viewModelScope` (*androidx.lifecycle:lifecycle-viewmodel-ktx*), то в
      качестве `LintFix` предложите замену `GlobalScope` → `viewModelScope`.
    * Если мы находимся внутри класса-наследника `Fragment` и в класспасе есть
      экстеншен `viewLifecycleOwner.lifecycleScope`(*androidx.lifecycle:lifecycle-runtime-ktx*), то
      в качестве LintFix предложите замену `GlobalScope` → `viewLifecycleOwner.lifecycleScope`.
2. Если `launch` запускается на `viewModelScope`, то в качестве элементов контекста не
   используется `Dispatchers.Main` и `SupervisorJob`. `Severity.ERROR `:
    * В качестве фикса уберите ненужные элементы контеста: `Dispatchers` или `SupervisorJob`.
      `viewModelScope(Dispatchers.Main + SupervisorJob()) `→ `viewModelScope()`

Обязательно покройте ваши детекторы тестами. Если протестируете `LintFix`, то будет совсем круто.
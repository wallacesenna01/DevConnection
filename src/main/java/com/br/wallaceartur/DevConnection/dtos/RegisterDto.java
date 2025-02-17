package com.br.wallaceartur.DevConnection.dtos;

import com.br.wallaceartur.DevConnection.enums.UserRole;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.lang.NonNull;

public record RegisterDto(@NotNull String email, @NonNull String password, @NotNull UserRole role) {
}

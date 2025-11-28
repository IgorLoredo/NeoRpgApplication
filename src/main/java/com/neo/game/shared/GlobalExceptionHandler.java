package com.neo.game.shared;

/**
 * Legacy/simple exception handler kept for reference.
 * The active GlobalExceptionHandler lives in
 * com.neo.game.shared.exception.GlobalExceptionHandler and is
 * richer (validation handling, structured ErrorResponse). This
 * class is intentionally NOT a Spring component to avoid duplicate
 * bean registration.
 */
public class GlobalExceptionHandler {
    // intentionally left non-annotated
}

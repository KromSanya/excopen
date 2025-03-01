import path from "path"
import react from "@vitejs/plugin-react"
import { defineConfig } from "vitest/config"
import svgr from 'vite-plugin-svgr';

export default defineConfig({
  assetsInclude: ['**/*.png', '**/*.jpg', '**/*.svg'],
  plugins: [react(), svgr()],
  test: {
    globals: true,
    environment: 'jsdom',
    setupFiles: './src/app/setupTests.ts',
  },
  resolve: {
    alias: {
      "@": path.resolve(__dirname, "./src"),
    },
  },
})
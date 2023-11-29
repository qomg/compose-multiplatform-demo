import { instantiate } from './ComposeMultiplatformDemo.uninstantiated.mjs';

await wasmSetup;

instantiate({ skia: Module['asm'] });
